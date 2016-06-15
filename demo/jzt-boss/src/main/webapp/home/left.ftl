<div class="nav">
        <ul class="nav" style="display: block;">
        <#list rules as rule>
        	 <#if rule.path==FirstMenu>
               <li class="cur"><a href="#"><i class="${rule.className!'' }"></i>${rule.permissionName!'' }</a>
              <ul class="sub-nav" style="display: block;">
             <#else>
               <li class=""><a href="#"><i class="${rule.className!'' }"></i>${rule.permissionName!'' }</a>
              <ul class="sub-nav">
             </#if>
	                <#list rule.sonList as r1>
                         <#if r1.path==SecondMenu>
                         	<li class="hover">${r1.permissionName!'' }
			             	<ul class="sub-navs cur" style="display: block;">
			             <#else>
			             	<li>${r1.permissionName!'' }
			             	<ul class="sub-navs">
			             </#if>
	                        	<#list r1.sonList as r12>
	                        		<#if r12.path==ThirdMenu>
						             	<li class="hover"><a href="${(r12.operationResource)!'javascript:void(0)' }?golableMenuPath=${(r12.path)!''}">${(r12.permissionName)!'' }</a></li>
						             <#else>
						             	<li><a href="${(r12.operationResource)!'javascript:void(0)' }?golableMenuPath=${(r12.path)!''}">${(r12.permissionName)!'' }</a></li>
						             </#if>
	                            	
	                        	</#list>
	                        </ul>
	                    </li>
	                 </#list>
                </ul>
                <div class="pop-sub-nav">
                    <span></span>
                    <#if (rule.sonList?exists)>
	                    <#if (rule.sonList?size!=0)>
		                    <p>
			                    <#list rule.sonList as r11>
			                        <a href="${(r11.operationResource)!'javascript:void(0)' }" target="right">${(r11.permissionName)!'' }</a>
			                    </#list>
		                    </p>
	                    </#if>
                    </#if>
                </div>
            </li>
          </#list>
        </ul>
</div>