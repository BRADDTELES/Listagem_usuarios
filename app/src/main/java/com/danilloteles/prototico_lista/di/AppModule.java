package com.danilloteles.prototico_lista.di;

import com.danilloteles.prototico_lista.data.UsuarioRepository;

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
    public UsuarioRepository provideUsuarioRepository() {
        return new UsuarioRepository();
    }

}
