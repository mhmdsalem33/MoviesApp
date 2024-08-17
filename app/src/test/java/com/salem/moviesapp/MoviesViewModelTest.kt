package com.salem.moviesapp
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salem.moviesapp.domain.usecases.GetMoviesUseCase
import com.salem.moviesapp.domain.usecases.RefreshMoviesUseCase
import com.salem.moviesapp.presentation.ui.activites.screens.movies_screen.MoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @MockK
    lateinit var refreshMoviesUseCase: RefreshMoviesUseCase

    private lateinit var viewModel: MoviesViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(testDispatcher)

        coEvery { refreshMoviesUseCase(any()) } just Runs

        viewModel = MoviesViewModel(getMoviesUseCase, refreshMoviesUseCase)
    }

    @Test
    fun `loadNextPage calls refreshMoviesUseCase with incremented page`() = runTest {
        viewModel.loadNextPage()
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { refreshMoviesUseCase(2) }
    }

}
