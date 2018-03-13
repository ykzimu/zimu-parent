package com.zimu.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.StringConverter;

/**
 * 
 * @author yangkun
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	static {
		registConverter();
	}

	public static Object getPrivateProperty(Object object, String propertyName)
			throws IllegalAccessException, NoSuchFieldException {
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		return field.get(object);
	}

	public static void registConverter() {
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new DateConverter(), Date.class);
	}

	public static void setPrivateProperty(Object object, String propertyName, Object newValue)
			throws IllegalAccessException, NoSuchFieldException {
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(object, newValue);
	}

	@SuppressWarnings("rawtypes")
	public static Object invokePrivateMethod(Object object, String methodName, Object[] params)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Method method = object.getClass().getDeclaredMethod(methodName, types);
		method.setAccessible(true);
		return method.invoke(object, params);
	}

	public static Object invokePrivateMethod(Object object, String methodName, Object param)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return invokePrivateMethod(object, methodName, new Object[] { param });
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object invokeStaticMethod(Class clazz, String methodName, Object[] params)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Method method = clazz.getMethod(methodName, types);
		// method.setAccessible(true);
		return method.invoke(null, params);
	}

	@SuppressWarnings("rawtypes")
	public static String reflectBeanToString(Object value, int depth) {
		String result = "";
		if (value instanceof Collection) {
			if (depth > 0) {
				Collection collection = (Collection) value;
				for (Object object : collection) {
					String objectString = reflectBeanToString(object, depth);
					result = result + "[" + objectString + "],";
				}
			}

		} else if (value.getClass().isArray()) {
			if (depth > 0) {
				Object[] objects = (Object[]) value;
				for (Object object : objects) {
					String objectString = reflectBeanToString(object, depth);
					result = result + "[" + objectString + "],";
				}
			}

		} else if (value.getClass().getName().startsWith("com.ibm.sc")) {
			if (depth > 0) {
				PropertyDescriptor[] propertyDescriptors = org.apache.commons.beanutils.PropertyUtils
						.getPropertyDescriptors(value);
				if (propertyDescriptors != null && propertyDescriptors.length > 0) {
					result = result + "(";
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						PropertyDescriptor[] objectPropertyDescriptors = org.apache.commons.beanutils.PropertyUtils
								.getPropertyDescriptors(Object.class);

						boolean ignore = false;
						for (PropertyDescriptor objectPropertyDescriptor : objectPropertyDescriptors) {
							if (propertyDescriptor.getName().equals(objectPropertyDescriptor.getName())) {
								ignore = true;
								break;
							}
						}

						if (ignore)
							continue;
						try {
							Object subValue = org.apache.commons.beanutils.PropertyUtils.getProperty(value,
									propertyDescriptor.getName());
							if (subValue != null) {
								String objectString = propertyDescriptor.getName() + ":"
										+ reflectBeanToString(subValue, depth - 1) + ",";
								result = result + objectString;
							}

						} catch (Throwable ex) {
							ex.printStackTrace();
						}
					}
					result = result + ")";

				}
			}

		} else {
			result = value.toString();
		}

		return result;
	}

}
