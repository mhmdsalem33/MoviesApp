package com.salem.moviesapp.domain.usecases

import com.salem.moviesapp.domain.repo.MoviesRepo
import javax.inject.Inject

open class GetMoviesUseCase @Inject constructor(
    private val moviesRepo : MoviesRepo
) {
    operator fun invoke() =   moviesRepo.getMoviesPagingSource()
}
