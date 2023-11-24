package tn.esprit.twin1.tpspring.Loggers;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    //    Advice 1 : consittuée du pointcat
    @Before("execution(* tn.esprit.twin1.tpspring.services.*.*(..))") // le string écrit est le pointcat
    //ce pointcat est constitué de * :
//    le 1er * concerne le type retour
//    la 2ème * représente n'importe quelle classe situées dans le directory services
//    la 3ème * représente n'importe quelle méthode
//    (..) représente nimporte quels  paramêtres d'une méthode
    public void logMethodEntry(JoinPoint joinPoint) { // cette méthode est une advice
        String name = joinPoint.getSignature().getName();
        // JointPoint est une méthode qui pointe sur la liste des méthodes ciblées par le pointcat (voir au-dessus)

        log.info("In method : " + name);
    }

//        Advice 2 : consittuée du pointcat

    @After("execution(* tn.esprit.twin1.tpspring.services.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Out of Method : " + name);

    }


}