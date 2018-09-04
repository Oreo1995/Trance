package com.trance.multi.trance.Util;

import android.service.media.MediaBrowserService;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

public class Util {
    public static <T> MediaBrowserService.Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(MediaBrowserService.Result.class, new Class[]{clazz});
        return new Gson().fromJson(reader, type);
    }
}
