package br.com.fiap.commons.config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Configuration
public class GsonConfig {

	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapterFactory(new EmptyListToNullFactory())
				.registerTypeAdapter(LocalDate.class, LocalDateConfig.jsonSerializerLocalDate())
				.registerTypeAdapter(LocalDate.class, LocalDateConfig.jsonDeserializerLocalDate())
				.registerTypeAdapter(LocalDateTime.class, LocalDateTimeConfig.jsonSerializerLocalDateTime())
				.registerTypeAdapter(LocalDateTime.class, LocalDateTimeConfig.jsonDeserializerLocalDateTime())
				.create();
	}

	@NoArgsConstructor(access = PRIVATE)
	static class LocalDateConfig {
		private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		static JsonSerializer<LocalDate> jsonSerializerLocalDate() {
			return (src, typeOfSrc, context) -> new JsonPrimitive(src.format(df));
		}

		static JsonDeserializer<LocalDate> jsonDeserializerLocalDate() {
			return (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString(), df);
		}
	}

	@NoArgsConstructor(access = PRIVATE)
	static class LocalDateTimeConfig {
		private static final DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

		private static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss" +
				"[.SSSSSSSSS]" +
				"[.SSSSSSSS]" +
				"[.SSSSSSS]" +
				"[.SSSSSS]" +
				"[.SSSSS]" +
				"[.SSSS]" +
				"[.SSS]");

		static JsonSerializer<LocalDateTime> jsonSerializerLocalDateTime() {
			return (src, typeOfSrc, context) -> new JsonPrimitive(src.format(dtf1));
		}

		static JsonDeserializer<LocalDateTime> jsonDeserializerLocalDateTime() {
			return (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), dtf2);
		}
	}

	private static class EmptyListToNullFactory implements TypeAdapterFactory {

		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			var rawType = type.getRawType();
			if (!List.class.isAssignableFrom(rawType))
				return null;

			@SuppressWarnings("unchecked")
			var delegate = (TypeAdapter<List<Object>>) (gson.getDelegateAdapter(this, type));

			@SuppressWarnings("unchecked")
			var adapter = (TypeAdapter<T>) new TypeAdapter<List<Object>>() {
				@Override
				public List<Object> read(JsonReader in) throws IOException {
					return delegate.read(in);
				}

				@Override
				public void write(JsonWriter out, List<Object> value) throws IOException {
					if (value == null || value.isEmpty())
						delegate.write(out, null);
					else
						delegate.write(out, value);
				}
			};
			return adapter;
		}
	}
}
