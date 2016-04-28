/*
 * Copyright (c) 2016 Sergey Glotov.
 *
 *
 * This file is part of ARC70.
 *
 * ARC70 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ARC70 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ARC70.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sergeich.autoradioclub.app.model.remote.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} which uses Jackson.
 * <p>
 * Because Jackson is so flexible in the types it supports, this converter assumes that it can
 * handle all types. If you are mixing JSON serialization with something else (such as protocol
 * buffers), you must {@linkplain Retrofit.Builder#addConverterFactory(Converter.Factory) add this
 * instance} last to allow the other converters a chance to see their types.
 */
public final class JacksonCsvConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link ObjectMapper} instance for conversion.
     */
    public static JacksonCsvConverterFactory create() {
        return create(new CsvMapper());
    }

    /**
     * Create an instance using {@code mapper} for conversion.
     */
    public static JacksonCsvConverterFactory create(CsvMapper mapper) {
        return new JacksonCsvConverterFactory(mapper);
    }

    private final CsvMapper mapper;

    private JacksonCsvConverterFactory(CsvMapper mapper) {
        if (mapper == null) throw new NullPointerException("mapper == null");
        this.mapper = mapper;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        JavaType javaType = mapper.getTypeFactory().constructType(type);
        CsvSchema csvSchema = mapper.schemaFor(javaType);
        ObjectReader reader = mapper.readerFor(javaType).with(csvSchema);
        return new JacksonCsvResponseBodyConverter<>(reader);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        JavaType javaType = mapper.getTypeFactory().constructType(type);
        ObjectWriter writer = mapper.writerWithType(javaType);
        return new JacksonCsvRequestBodyConverter<>(writer);
    }
}
