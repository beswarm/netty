package com.beswarm.codelearning.netty4.demo2_embedChannel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * desc:
 *
 * @author <a href="kangqinghua#wxchina.com">beswarm</a>
 * @version 1.0.0
 * @date 2018-05-14
 */
public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() throws Exception {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * (-1));
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(channel.writeOutbound(buf));
        assertTrue(channel.finish());

        for (int i = 1; i < 10; i++) {
            assertEquals(i, channel.readOutbound());
        }
        assertNull(channel.readOutbound());

    }
}