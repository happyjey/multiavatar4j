package com.sabazed.utils;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabazed.utils.exception.ResourceLoadingException;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class HelperUtils {

	private static final String OUTPUT_DIR = "./output/";

	public static <T> T loadResource(String path, Class<T> cls) throws ResourceLoadingException {
		return loadResource(path, cls, List.of());
	}

	public static <T> T loadResource(String path, Class<T> cls, List<Module> modules) throws ResourceLoadingException {
		val objectMapper = new ObjectMapper();
		try {
			val content = getResourceContent(path);
			modules.forEach(objectMapper::registerModule);
			return objectMapper.readValue(content, cls);
		} catch (IOException e) {
			throw new ResourceLoadingException(path, e);
		}
	}

	private static String getResourceContent(String path) throws IOException {
		try (val in = HelperUtils.class.getResourceAsStream(path);
				 val reader = new BufferedReader(new InputStreamReader(in))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			throw new ResourceLoadingException(path, e);
		}
	}

	public static void writeResourceContent(String path, String content) throws IOException {
		Files.createDirectories(Paths.get(OUTPUT_DIR));
		Files.writeString(new File(OUTPUT_DIR + path).toPath(), content);
	}

	public static void writeResourceContent(String path, byte[] content) throws IOException {
		Files.createDirectories(Paths.get(OUTPUT_DIR));
		Files.write(new File(OUTPUT_DIR + path).toPath(), content);
	}

}
