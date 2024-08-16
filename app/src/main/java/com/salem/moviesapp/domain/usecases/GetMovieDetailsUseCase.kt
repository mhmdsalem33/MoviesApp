package com.salem.moviesapp.domain.usecases

import com.salem.moviesapp.domain.repo.MoviesRepo
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo
) {
    suspend operator fun invoke( movieId : Int ) =  moviesRepo.getMovieDetails(movieId)
}