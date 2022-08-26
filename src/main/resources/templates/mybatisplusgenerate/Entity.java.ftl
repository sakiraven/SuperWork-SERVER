package ${package.Entity};

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
<#list table.importPackages as pkg>
    <#if pkg == "java.util.Date">
        import ${pkg};
    </#if>
</#list>

/**
* ${table.name} : ${table.comment!}
*/
@Data
public class ${entity}  implements Serializable {

private static final long serialVersionUID = 1L;
<#-- ----------  属性私有化  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.keyFlag>
    <#-- 主键 -->
        /**
        * 主键 : ${field.name},  ${field.comment!}
        */
        @TableId(value = "${field.name}" , type = IdType.ASSIGN_ID)
    <#-- 普通字段 -->
    <#elseif !field.keyFlag>
        /**
        * ${field.name},  ${field.comment!}
        */
        @TableField(value = "${field.name}")
    </#if>
<#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
        @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
        @TableLogic
    </#if>
    <#if field.propertyType == "LocalDateTime">
        private Date ${field.propertyName};
    </#if>
    <#if field.propertyType != "LocalDateTime">
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>

<#------------  构造函数   ----------- -->
<#if !entityLombokModel>
    public ${entity}(<#list table.fields as field><#if field.propertyType == "LocalDateTime">Date ${field.propertyName}</#if><#if field.propertyType != "LocalDateTime">${field.propertyType} ${field.propertyName}</#if><#sep>,</#list>){
    <#list table.fields as field>
        this.${field.propertyName} = ${field.propertyName};
    </#list>
    }
    public ${entity}(){
    }
</#if>

<#------------  getter.setter封装  ---------->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
        public <#if field.propertyType == "LocalDateTime">Date</#if><#if field.propertyType != "LocalDateTime">${field.propertyType}</#if> ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
        }
        <#if entityBuilderModel>
            public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
            public void set${field.capitalName}(<#if field.propertyType == "LocalDateTime">Date</#if><#if field.propertyType != "LocalDateTime">${field.propertyType}</#if> ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
            return this;
        </#if>
        }
    </#list>
</#if>

<#-------------  重写toString()  ----------------->
<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}