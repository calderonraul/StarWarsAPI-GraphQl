package com.example.starwarsapigraphql.di

import android.app.Application
import androidx.room.Room
import com.example.data.PeopleRepositoryImpl
import com.example.data.api.PersonsAPI
import com.example.data.database.PersonsDatabase
import com.example.data.mappers.apolloMapper.ApolloClassToModelMapper
import com.example.data.mappers.personsMapper.PersonMapper
import com.example.domain.repository.PersonsRepository
import com.example.domain.useCase.GetPeopleUseCase
import com.example.data.database.PersonsDao
import com.example.data.mappers.apolloMapper.ApolloClassPersonXMapper
import com.example.data.mappers.personDetailMapper.PersonDetailMapper
import com.example.data.pager.PersonsSource
import com.example.domain.useCase.GetPeopleDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providePersonsAPi(): PersonsAPI {
        return PersonsAPI()
    }
}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val NAME = "persons"

    @Provides
    @Singleton
    fun provideDatabase(application: Application): PersonsDatabase {
        return Room.databaseBuilder(
            application,
            PersonsDatabase::class.java, NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePersonsDao(database: PersonsDatabase): PersonsDao {
        return database.personsDao
    }

}

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {
    @Provides
    @Singleton
    fun providePersonMapper(): PersonMapper {
        return PersonMapper()
    }

    @Provides
    @Singleton
    fun provideApolloMapper(): ApolloClassToModelMapper{
        return ApolloClassToModelMapper()
    }

    @Provides
    @Singleton
    fun provideApolloPersonMapper(): ApolloClassPersonXMapper{
        return ApolloClassPersonXMapper()
    }

    @Provides
    @Singleton
    fun providePersonDetailMapper():PersonDetailMapper{
        return PersonDetailMapper()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Provides
    fun provideRepository(
        api: PersonsAPI,
        dao: PersonsDao,
        apolloMapper: ApolloClassToModelMapper,
        personMapper: PersonMapper,
        apolloPersonMapper: ApolloClassPersonXMapper,
        personDetailMapper: PersonDetailMapper
    ):PersonsRepository{
        return PeopleRepositoryImpl(dao,personMapper,api,apolloMapper,apolloPersonMapper,personDetailMapper)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object PagerModule{
    @Provides
    fun providePagerSource(api: PersonsAPI): PersonsSource {
        return PersonsSource(api)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule{
    @Provides
    @Singleton
    fun providePersonsUseCase(repository: PersonsRepository):GetPeopleUseCase{
        return GetPeopleUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePeopleDetailUseCase(repository: PersonsRepository):GetPeopleDetailUseCase{
        return GetPeopleDetailUseCase(repository)
    }
}
