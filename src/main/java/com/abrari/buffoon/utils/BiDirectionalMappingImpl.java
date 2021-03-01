package com.abrari.buffoon.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Setter
@Component
public class BiDirectionalMappingImpl<A, B> implements BiDirectionalMapping<A, B>{

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ModelMapper modelMapper;

    @NonNull
    private Class<A> classOfA;

    @NonNull
    private Class<B> classOfB;

    @Override
    public A mapToLeft(B b){
        return modelMapper.map(b, classOfA);
    }
    @Override
    public B mapToRight(A a){
        return modelMapper.map(a, classOfB);
    }


}
