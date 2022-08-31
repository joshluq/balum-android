package pe.joshluq.balum.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.joshluq.balum.common.resource.ResourceProvider
import pe.joshluq.balum.common.resource.ResourceProviderImpl
import java.lang.ref.WeakReference
import javax.inject.Singleton


@Module(includes = [RepositoryModule::class, UseCaseModule::class])
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideWeekContext(@ApplicationContext appContext: Context): WeakReference<Context> =
        WeakReference(appContext)

    @Singleton
    @Provides
    fun provideResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider =
        resourceProvider

}