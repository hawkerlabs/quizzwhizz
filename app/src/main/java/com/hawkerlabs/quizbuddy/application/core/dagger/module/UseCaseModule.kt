package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.domain.category.GetDisplayCategoriesUseCase
import com.hawkerlabs.quizbuddy.domain.category.impl.GetDisplayCategoriesUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.question.impl.GetQuestionsByCategoryUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import com.hawkerlabs.quizbuddy.domain.session.impl.SessionUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {


    @Binds
    abstract fun provideSessionUseCase(sessionUseCase: SessionUseCaseImpl): SessionUseCase

    @Binds
    abstract fun provideGetDisplayCategoriesUseCase(getDisplayCategoriesUseCase: GetDisplayCategoriesUseCaseImpl): GetDisplayCategoriesUseCase

    @Binds
    abstract fun provideGetQuestionsByCategoryUseCase(sessionUseCase: GetQuestionsByCategoryUseCaseImpl): GetQuestionsByCategoryUseCase





}