package com.ccspart2.projectcerberusadmincompose.di

import com.ccspart2.projectcerberusadmincompose.data.repository.EmployeeRepositoryImpl
import com.ccspart2.projectcerberusadmincompose.data.repository.EmployeesRepository
import com.ccspart2.projectcerberusadmincompose.data.repository.LocationRepositoryImpl
import com.ccspart2.projectcerberusadmincompose.data.repository.LocationsRepository
import com.ccspart2.projectcerberusadmincompose.data.source.EmployeeDataSource
import com.ccspart2.projectcerberusadmincompose.data.source.EmployeeDataSourceImpl
import com.ccspart2.projectcerberusadmincompose.data.source.LocationDataSource
import com.ccspart2.projectcerberusadmincompose.data.source.LocationDataSourceImpl
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.domain.LocationsUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // Employee Block

    @Provides
    @Singleton
    fun provideEmployeeDataSource(firestore: FirebaseFirestore): EmployeeDataSource {
        return EmployeeDataSourceImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(dataSource: EmployeeDataSource): EmployeesRepository {
        return EmployeeRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideEmployeesUseCases(repository: EmployeesRepository): EmployeesUseCases {
        return EmployeesUseCases(repository)
    }

    // Location Block

    @Provides
    @Singleton
    fun provideLocationDataSource(firestore: FirebaseFirestore): LocationDataSource {
        return LocationDataSourceImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(dataSource: LocationDataSource): LocationsRepository {
        return LocationRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLocationsUseCases(repository: LocationsRepository): LocationsUseCases {
        return LocationsUseCases(repository)
    }
}
