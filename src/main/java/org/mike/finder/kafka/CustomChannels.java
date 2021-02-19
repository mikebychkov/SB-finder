package org.mike.finder.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
    @Input("inboundPostsChanges")
    SubscribableChannel posts();
}
