package com.ccspart2.projectcerberusadmincompose.di

import com.ccspart2.projectcerberusadmincompose.data.repository.FirestoreRepository
import com.ccspart2.projectcerberusadmincompose.data.repository.FirestoreRepositoryImpl
import com.ccspart2.projectcerberusadmincompose.data.source.FirestoreDataSource
import com.ccspart2.projectcerberusadmincompose.data.source.FirestoreDataSourceImpl
import com.ccspart2.projectcerberusadmincompose.domain.AddDataUseCase
import com.ccspart2.projectcerberusadmincompose.domain.GetDataUseCase
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

    @Provides
    @Singleton
    fun provideFirestoreDataSource(
        firestore: FirebaseFirestore
    ): FirestoreDataSource {
        return FirestoreDataSourceImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideFirestoreRepository(
        dataSource: FirestoreDataSource
    ): FirestoreRepository {
        return FirestoreRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetDataUseCase(
        repository: FirestoreRepository
    ): GetDataUseCase {
        return GetDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddDataUseCase(repository: FirestoreRepository): AddDataUseCase {
        return AddDataUseCase(repository)
    }
}
