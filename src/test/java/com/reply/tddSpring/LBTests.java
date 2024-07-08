package com.reply.tddSpring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LBTests {
    public static final int OVER_MAXIMUM = 11;
    private LoadBalancerService systemUnderTest;

    @BeforeEach
    void setUp() {
        systemUnderTest = new LoadBalancerServiceImpl();
    }

    @Test
    void shouldCreateEmptyLoadBalancerRegistry() {
        final Set<URI> serverSet = systemUnderTest.getRegistry();

        assertThat(serverSet).isEmpty();
    }

    @Test
    void shouldContainMaximumTenElements() {
        final Set<URI> serverSet = systemUnderTest.getRegistry();

        populateRegistry(OVER_MAXIMUM);

        assertThat(serverSet).hasSize(LoadBalancerServiceImpl.MAX_SIZE);
    }

    @Test
    void shouldGetRandomAddress() {
        populateRegistry(LoadBalancerServiceImpl.MAX_SIZE);

        URI server = systemUnderTest.getServer();

        System.out.println(server.toString());
        assertThat(server).isNotNull();
        assertThat(server.getHost()).isEqualTo("localhost");
    }

    private void populateRegistry(int totalCount) {
        IntStream.rangeClosed(1, totalCount).forEachOrdered(i -> systemUnderTest.register(URI.create("http://localhost:" + i)));
    }
}
