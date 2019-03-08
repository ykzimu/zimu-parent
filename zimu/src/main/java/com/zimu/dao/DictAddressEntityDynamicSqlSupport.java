package com.zimu.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DictAddressEntityDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DictAddressEntity dictAddressEntity = new DictAddressEntity();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = dictAddressEntity.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> code = dictAddressEntity.code;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> parentCode = dictAddressEntity.parentCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> level = dictAddressEntity.level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = dictAddressEntity.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sortNo = dictAddressEntity.sortNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> delFlag = dictAddressEntity.delFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createBy = dictAddressEntity.createBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createDate = dictAddressEntity.createDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updateBy = dictAddressEntity.updateBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateDate = dictAddressEntity.updateDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = dictAddressEntity.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DictAddressEntity extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> parentCode = column("parent_code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> level = column("level", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sortNo = column("sort_no", JDBCType.INTEGER);

        public final SqlColumn<Integer> delFlag = column("del_flag", JDBCType.INTEGER);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createDate = column("create_date", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateDate = column("update_date", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public DictAddressEntity() {
            super("t_dict_address");
        }
    }
}