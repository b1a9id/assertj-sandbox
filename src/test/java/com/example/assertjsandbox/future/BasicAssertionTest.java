package com.example.assertjsandbox.future;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class BasicAssertionTest {

	@Test
	void futureSucceedsWithin() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<String> future = executorService.submit(() -> {
			Thread.sleep(100);
			return "Complete!!";
		});

		Assertions.assertThat(future)
				.succeedsWithin(200, TimeUnit.MILLISECONDS)
				.isEqualTo("Complete!!");

		Duration timeout = Duration.ofMillis(200);
		Assertions.assertThat(future).succeedsWithin(timeout).isEqualTo("Complete!!");

		Assertions.assertThat(future).succeedsWithin(50, TimeUnit.MILLISECONDS);
	}
}
