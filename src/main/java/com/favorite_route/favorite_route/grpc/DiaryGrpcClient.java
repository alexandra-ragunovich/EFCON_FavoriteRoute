package com.favorite_route.favorite_route.grpc;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryGrpcClient {

    @GrpcClient("diary-client")
    private com.travel.grpc.DiaryGrpcServiceGrpc.DiaryGrpcServiceBlockingStub grpcStub;

    public Long createDiaryPost(Long userId, String title, String content, String location, String duration) {

        com.travel.grpc.GrpcDiaryPostRequest request = com.travel.grpc.GrpcDiaryPostRequest.newBuilder()
                .setUserId(userId)
                .setTitle(title)
                .setContent(content)
                .setLocation(location)
                .setIsPublic(true)
                .setDuration(duration)
                .build();

        com.travel.grpc.GrpcDiaryPostResponse response = grpcStub.createDiaryPost(request);

        if ("SUCCESS".equals(response.getStatus())) {
            return response.getPostId();
        } else {
            throw new RuntimeException("Не удалось создать пост в дневнике");
        }
    }
}
