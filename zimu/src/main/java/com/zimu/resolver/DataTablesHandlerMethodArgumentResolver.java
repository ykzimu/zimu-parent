package com.zimu.resolver;

import com.zimu.domain.info.DataTablesInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DataTablesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String COLUMNS_REGEX = "^columns\\[\\d+\\]\\[data\\]$";
    private static final String ORDER_REGEX = "^order\\[\\d+\\]\\[column\\]$";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(DataTablesInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        DataTablesInfo dataTablesInfo = new DataTablesInfo();
        try {
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

            int columnsSize = 0;
            int orderSize = 0;
            Pattern cp = Pattern.compile(COLUMNS_REGEX);
            Pattern op = Pattern.compile(ORDER_REGEX);

            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                Matcher cm = cp.matcher(name);
                if (cm.matches()) {
                    columnsSize++;
                }

                Matcher om = op.matcher(name);
                if (om.matches()) {
                    orderSize++;
                }
            }

            List<DataTablesInfo.Column> columns = new ArrayList<>(columnsSize);
            for (int i = 0; i < columnsSize; i++) {

                DataTablesInfo.Column column = new DataTablesInfo.Column();
                DataTablesInfo.Search search = new DataTablesInfo.Search();

                String data = request.getParameter("columns[" + i + "][data]");
                column.setData(data);

                String name = request.getParameter("columns[" + i + "][name]");
                column.setName(name);

                String searchable = request.getParameter("columns[" + i + "][searchable]");
                column.setSearchable(Boolean.valueOf(searchable));

                String orderable = request.getParameter("columns[" + i + "][orderable]");
                column.setOrderable(Boolean.valueOf(orderable));

                String value = request.getParameter("columns[" + i + "][search][value]");
                search.setValue(value);

                String regex = request.getParameter("columns[" + i + "][search][regex]");
                search.setRegex(Boolean.valueOf(regex));

                column.setSearch(search);
                columns.add(column);
            }


            List<DataTablesInfo.Order> order = new ArrayList<>(orderSize);
            for (int i = 0; i < orderSize; i++) {
                DataTablesInfo.Order o = new DataTablesInfo.Order();
                String dir = request.getParameter("order[" + i + "][dir]");
                o.setDir(dir);
                String column = request.getParameter("order[" + i + "][column]");
                o.setColumn(Integer.parseInt(column));
                order.add(o);
            }


            dataTablesInfo.setDraw(Long.valueOf(request.getParameter("draw")));
            dataTablesInfo.setStart(Long.valueOf(request.getParameter("start")));
            dataTablesInfo.setLength(Integer.valueOf(request.getParameter("length")));

            DataTablesInfo.Search search = new DataTablesInfo.Search();
            search.setValue(request.getParameter("search[value]"));
            search.setRegex(Boolean.valueOf(request.getParameter("search[regex]")));
            dataTablesInfo.setSearch(search);

            dataTablesInfo.setColumns(columns);
            dataTablesInfo.setOrder(order);
        } catch (Exception e) {
            log.error("DataTablesInfo 转换失败！", e);
        }
        return dataTablesInfo;
    }
}
