//package com.haushive.tyr1.security;
//
//import java.util.Collection;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import com.haushive.tyr1.exception.UnauthenticatedAccessException;
//
//@Aspect
//@Component
//@Order(1)
//public class SecurityGraphQLAspect {
//
//    /**
//     * All graphQLResolver methods can be called only by authenticated user.
//     *
//     * @Unsecured annotated methods are excluded
//     */
//
//    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured()")
//    public void doSecurityCheck() {
//        if (SecurityContextHolder.getContext() == null ||
//                SecurityContextHolder.getContext().getAuthentication() == null ||
//                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
//                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
//            throw new UnauthenticatedAccessException("Please login to continue.");
//        }
//    }
//
//    /**
//     * @AdminSecured annotated methods can be called only by admin authenticated user.
//     */
//    @Before("isMethodAnnotatedAsAdminUnsecured()")
//    public void doAdminSecurityCheck() {
//        if (!isAuthorized()) {
//            throw new UnauthenticatedAccessException("Sorry, you do not have permission to perform this task.");
//        }
//    }
//
//
//    /**
//     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver} as
//     * {@code UserMutation}, {@code UserQuery}
//     * extend GraphQLResolver interface
//     */
//    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
//    private void allGraphQLResolverMethods() {
//        //leave empty
//    }
//
//    /**
//     * Matches all beans in com.zerofiltre.samplegraphqlerrorhandling package
//     */
//    @Pointcut("within(com.haushive.tyr1..*)")
//    private void isDefinedInApplication() {
//        //leave empty
//    }
//
//    /**
//     * Any method annotated with @Unsecured
//     */
//    @Pointcut("@annotation(com.haushive.tyr1.security.Unsecured)")
//    private void isMethodAnnotatedAsUnsecured() {
//        //leave empty
//    }
//
//    /**
//     * Any method annotated with @AdminSecured
//     */
//    @Pointcut("@annotation(com.haushive.tyr1.security.AdminSecured)")
//    private void isMethodAnnotatedAsAdminUnsecured() {
//        //leave empty
//    }
//
//    private boolean isAuthorized() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority auth : authorities) {
//                if (auth.getAuthority().equals("ROLE_ADMIN"))
//                    return true;
//            }
//        }
//        return false;
//    }
//}
