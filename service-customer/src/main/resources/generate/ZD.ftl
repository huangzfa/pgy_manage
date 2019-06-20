package com.pgy.common.dic;


public interface ZD {
<#list dctList as d>	
	
<#if d.pid!=0>

    <#if d.dataType == "String">
    public String ${d.dicVar} = "${d.dicVal}";
    </#if>
    <#if d.dataType == "int">
    public int ${d.dicVar} = ${d.dicVal};
    </#if>
    <#if d.dataType == "boolean">
    public boolean ${d.dicVar} = ${d.dicVal};
</#if>
<#else>
	/**
	 *  ${d.dicCode}
	 */
    public String ${d.dicVar} = "${d.dicVal}";
</#if>
</#list>
}