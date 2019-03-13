package br.com.alura.estoqueapi.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Aspect
@Component
class ApplicationLogger(val logger: Logger = LoggerFactory.getLogger(ApplicationLogger::class.java)) {

    @Pointcut("execution(* br.com.alura.estoqueapi..service..*(..))")
    fun services() {
    }

    @Pointcut("execution(* br.com.alura.estoqueapi..controller..*(..))")
    fun controllers() {
    }

    @Around("services() || controllers()")
    @Throws(Throwable::class)
    fun profile(joinPoint: ProceedingJoinPoint): Any {
        val classe = joinPoint.signature.declaringTypeName
        val metodo = joinPoint.signature.name
        val parametros = joinPoint.args
        return try {
            joinPoint.proceed()
        } catch (e: IllegalStateException) {
            // Evita o lançamento de exception desnecessária em funções que retornam Unit
        } finally {
            val log = montaLog(classe, metodo, parametros)
            logger.info(log)
        }
    }

    private fun montaLog(classe: String?,
                         metodo: String?,
                         parametros: Array<Any>): String {
        val classComMetodo = "$classe - $metodo"
        if (parametros.isNotEmpty()) {
            return classComMetodo + " args: ${parametros.toList()}"
        }
        return classComMetodo
    }

}
