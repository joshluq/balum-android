package pe.joshluq.balum.domain.usecase

interface UseCase<I, O> {

    suspend operator fun invoke(params: I): Result<O>

}