package com.danilloteles.prototico_lista.di;

import com.danilloteles.prototico_lista.data.ClienteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public ClienteRepository provideClienteRepository() {
        return new ClienteRepository();
    }

}
