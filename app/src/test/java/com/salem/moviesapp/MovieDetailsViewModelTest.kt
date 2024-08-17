package com.salem.moviesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.domain.usecases.GetMovieDetailsUseCase
import com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MovieDetailsViewModel
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getMoviesDetails emits correct movie details`() = runTest {
        val movieId      =  562
        val movieModel   =  MovieModel(
            releaseDate  =  "2024-08-17",
            title        =  "Sample Movie",
            posterPath   =  "https://image.tmdb.org/t/p/w500/fn3aXwADNC0nmJ51GYnkrOseVgB.jpg",
            overview     =  "the movies overview",
            genres       =  emptyList()
        )
        val flow: Flow<ResponseState<MovieModel>> = flowOf(ResponseState.Success(movieModel))
        coEvery { getMovieDetailsUseCase(movieId) } returns flow

        viewModel.getMoviesDetails(movieId)

        val result = viewModel.movieDetails
            .filter { it is ResponseState.Success }
            .map { it as ResponseState.Success<MovieModel> }
            .firstOrNull()

        assertTrue(result is ResponseState.Success)
        assertEquals(movieModel, (result as ResponseState.Success).data)
    }
}
