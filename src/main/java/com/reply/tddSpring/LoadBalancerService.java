package com.reply.tddSpring;

import java.net.URI;
import java.util.Set;

public interface LoadBalancerService {
    Set<URI> getRegistry();

    void register(URI uri);

    URI getServer();
}
