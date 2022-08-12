package com.lvmoney.frame.sync.netty.socket.config;

import com.lvmoney.frame.sync.netty.socket.web.interceptor.UserAuthInteceptor;
import com.lvmoney.frame.sync.netty.socket.web.websocket.WebSocketChildChannelHandler;
import com.lvmoney.frame.sync.netty.socket.web.websocket.WebSocketServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Resource(name = "webSocketChildChannelHandler")
    private WebSocketChildChannelHandler webSocketChildChannelHandler;

    @Bean
    public WebSocketServer getWebSocketServer() {
        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.setPort(3333);
        webSocketServer.setChildChannelHandler(webSocketChildChannelHandler);
        return webSocketServer;
    }

    @Bean("bossGroup")
    public EventLoopGroup getBossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean("workerGroup")
    public EventLoopGroup getWorkerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap getServerBootstrap() {
        return new ServerBootstrap();
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthInteceptor()).addPathPatterns("/chatroom/**");
    }

}
