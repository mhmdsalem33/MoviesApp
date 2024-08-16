package com.salem.moviesapp.domain.usecases

import com.salem.moviesapp.domain.repo.MoviesRepo
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor( private  val moviesRepo: MoviesRepo ) {
    suspend operator fun invoke( page : Int ) = moviesRepo.fetchAndStoreMovies( page )
}