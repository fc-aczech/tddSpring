package com.reply.tddSpring;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class LoadBalancerServiceImpl implements LoadBalancerService {
    protected static final int MAX_SIZE = 10;

    private final Set<URI> registry = new HashSet<>();

    @Override
    public Set<URI> getRegistry() {
        return this.registry;
    }

    @Override
    public void register(URI uri) {
        if (registry.size() >= MAX_SIZE) {
            return;
        }
        this.registry.add(uri);
    }

    @Override
    public URI getServer() {
        if (this.registry.isEmpty()) {
            throw new IllegalStateException("No server available");
        }
        return new ArrayList<>(registry).get(new Random().nextInt(this.registry.size()));
    }
}