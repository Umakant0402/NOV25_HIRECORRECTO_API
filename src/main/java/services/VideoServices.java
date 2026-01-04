package services;

import base.APIControlActions;
import base.ScreeningControl;
import entity.videoPOJO.VideoAnswerAnalysisPayload;
import entity.videoPOJO.VideoAnswerPayload;
import entity.videoPOJO.VideoFileGeneratorPayload;
import io.restassured.response.Response;
import utility.JavaToJSON;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoServices extends APIControlActions {


    public Response submitVideoAnswer(String questionId, String experience, String filePath) {
        Response videoFileResponse = generateVideoURL();
        String videoFileID = videoFileResponse.jsonPath().getString("fileId");
        String videoFileUploadURL = videoFileResponse.jsonPath().getString("azureUpload.uploadUrl");

        String payload = getPayloadForVideoAnswer(questionId, ScreeningControl.jobRoleID, experience, ScreeningControl.jobApplicationID, videoFileID);
        setBody(payload);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        Response videoAnswerResponse = executePatchAPI("/api/candidateScreening/update-candidate-result/" + ScreeningControl.candidateScreeningId);
        uploadVideoFileToAzure(videoFileUploadURL, filePath);

        String question = "<p>can you tell me all the HTTP methods in details like what exactly the method will work and when to use which methods</p>";
        String skillName = "api";
        String maxTime = "3";
        Response response = analyzeVideoResponseUri(videoFileID, question, questionId, skillName, maxTime, experience);
        System.out.println(response.statusCode());
        return videoAnswerResponse;
    }

    private String getPayloadForVideoAnswer(String questionId, String jobRoleId, String experience, String jobApplicationId, String answerVideoFileId) {
        VideoAnswerPayload payload =
                VideoAnswerPayload.builder()
                        .questionId(questionId)
                        .type("video")
                        .skill("api")
                        .jobRoleId(jobRoleId)
                        .experience(experience)
                        .jobApplicationId(jobApplicationId)
                        .timeSpent(33)
                        .fullScreenExitCount(0)
                        .tabSwitchCount(0)
                        .candidateAnswer(null)
                        .copyPasteAnalysis(
                                VideoAnswerPayload.CopyPasteAnalysis.builder()
                                        .totalDuration(53958)
                                        .totalCopyEvents(0)
                                        .questionCopyCount(0)
                                        .optionCopyCount(0)
                                        .fullQuestionCopyCount(0)
                                        .hasQuestionCopying(false)
                                        .hasOptionCopying(false)
                                        .hasFullQuestionCopying(false)
                                        .riskScore(0)
                                        .riskLevel("low")
                                        .isSuspicious(false)
                                        .copyBreakdown(
                                                VideoAnswerPayload.CopyBreakdown.builder()
                                                        .questionCopies(
                                                                VideoAnswerPayload.QuestionCopies.builder()
                                                                        .count(0)
                                                                        .averageLength(0)
                                                                        .timestamps(List.of())
                                                                        .build()
                                                        )
                                                        .optionCopies(
                                                                VideoAnswerPayload.OptionCopies.builder()
                                                                        .count(0)
                                                                        .averageLength(0)
                                                                        .timestamps(List.of())
                                                                        .build()
                                                        )
                                                        .fullQuestionCopies(
                                                                VideoAnswerPayload.FullQuestionCopies.builder()
                                                                        .count(0)
                                                                        .averageLength(0)
                                                                        .timestamps(List.of())
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .sessionId("copypaste_1766289681912_b90lsd3fa")
                                        .analysisVersion("2.0-mcq-focused")
                                        .timestamp("2025-12-21T04:02:15.870Z")
                                        .build()
                        )
                        .hasCopyPasteAnalysis(true)
                        .answerVideoFileId(answerVideoFileId)
                        .retakes(0)
                        .build();

        return JavaToJSON.convertToJSON(payload);
    }

    private Response analyzeVideoResponseUri(String fileId, String question, String questionId,

                                             String skillName, String maxTime, String experience) {
        VideoAnswerAnalysisPayload videoAnswerAnalysisPayload = VideoAnswerAnalysisPayload.builder()
                .fileId(fileId)
                .question(question)
                .questionId(questionId)
                .candidateScreeningId(ScreeningControl.candidateScreeningId)
                .candidateApplicationId(ScreeningControl.jobApplicationID)
                .skillName(skillName)
                .type("video")
                .jobRoleId(ScreeningControl.jobRoleID)
                .maxTime(maxTime)
                .experience(experience)
                .copyPasteAnalysis(
                        VideoAnswerAnalysisPayload.CopyPasteAnalysis.builder()
                                .totalDuration(53779)
                                .totalCopyEvents(0)
                                .questionCopyCount(0)
                                .optionCopyCount(0)
                                .fullQuestionCopyCount(0)
                                .hasQuestionCopying(false)
                                .hasOptionCopying(false)
                                .hasFullQuestionCopying(false)
                                .riskScore(0)
                                .riskLevel("low")
                                .isSuspicious(false)
                                .copyBreakdown(
                                        VideoAnswerAnalysisPayload.CopyBreakdown.builder()
                                                .questionCopies(
                                                        VideoAnswerAnalysisPayload.QuestionCopies.builder()
                                                                .count(0)
                                                                .averageLength(0)
                                                                .timestamps(List.of())
                                                                .build()
                                                )
                                                .optionCopies(
                                                        VideoAnswerAnalysisPayload.OptionCopies.builder()
                                                                .count(0)
                                                                .averageLength(0)
                                                                .timestamps(List.of())
                                                                .build()
                                                )
                                                .fullQuestionCopies(
                                                        VideoAnswerAnalysisPayload.FullQuestionCopies.builder()
                                                                .count(0)
                                                                .averageLength(0)
                                                                .timestamps(List.of())
                                                                .build()
                                                )
                                                .build()
                                )
                                .sessionId("copypaste_1766289681912_b90lsd3fa")
                                .analysisVersion("2.0-mcq-focused")
                                .timestamp("2025-12-21T04:02:15.691Z")
                                .build()
                )
                .hasCopyPasteAnalysis(true)
                .fullScreenExitCount(0)
                .tabSwitchCount(0)
                .build();

        String jsonPayload = JavaToJSON.convertToJSON(videoAnswerAnalysisPayload);
        setBody(jsonPayload);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        return executePostAPI("/api/candidateScreening/analyze-response-uri");
    }

    private Response uploadVideoFileToAzure(String azureURL, String filePath) {
        Path path = Paths.get(filePath);
//        Path path = Paths.get(new File("C:\\Users\\vegadah\\Downloads\\hireCorrecto_Automation-master\\hireCorrecto_Automation-master\\target\\videoEvidence\\279d421cbfa7987a708579b0d05f7460.mp4").getAbsolutePath());
        Response response = null;
        try {
            resetRequestBuilder();
            setHeader("x-ms-blob-type", "BlockBlob");
            byte[] fileContent = Files.readAllBytes(path);
            setBody(fileContent);
            response = executeMultipartPutAPIWithFullUrl(azureURL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private Response generateVideoURL() {
        VideoFileGeneratorPayload videoFilePayload = VideoFileGeneratorPayload.builder()
                .originalFilename("recordedVideo.webm")
                .fileType("video")
                .mimeType("video/webm")
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        String jsonPayload = JavaToJSON.convertToJSON(videoFilePayload);
        setBody(jsonPayload);

        return executePostAPI("/api/candidateScreening/generate-upload-url");
    }

}
