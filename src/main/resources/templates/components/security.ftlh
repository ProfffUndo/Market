<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        usrId = user.id
        isAdmin = user.isAdmin()
        isManager = user.isManager()
       currentUserId = user.getId()
    >
<#else>
    <#assign name = "unknown"
    isAdmin = false
    currentUserId = -1>
</#if>