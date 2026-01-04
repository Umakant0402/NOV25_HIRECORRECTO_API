package services;

import base.APIControlActions;
import base.ScreeningControl;
import entity.audioPOJO.AudioAnswerPayload;
import entity.audioPOJO.AudioFileGeneratorPayload;
import entity.audioPOJO.AudioSubmitAnswerPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import utility.JavaToJSON;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AudioService extends APIControlActions {

    public Response submitAudioAnswer(String questionId, String expi, String audioFilePath) {
        Response audioFileResponse = generateAudioURL();
        String audioFileID = audioFileResponse.jsonPath().getString("fileId");
        String audioFileUploadURL = audioFileResponse.jsonPath().getString("azureUpload.uploadUrl");

        uploadAudioFileToAzure(audioFileUploadURL, audioFilePath);
        String audioFilePayload = getAudioAnswerPayload(audioFileID, questionId);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);
        setBody(audioFilePayload);
        Response analyzResponseUriResponse = executePostAPI("/api/candidateScreening/analyze-response-uri");
        Assert.assertEquals(200, analyzResponseUriResponse.statusCode(), "Expected HTTP 200 for Audio Analyze Response URI");

        String audiAnswerPayload = getCandidateAudioAnswerPayload(questionId, expi, audioFileID);

        setHeaders(headers);
        setBody(audiAnswerPayload);
        Response aduioAnswerResponse = executePatchAPI("/api/candidateScreening/update-candidate-result/" + ScreeningControl.candidateScreeningId);

        return aduioAnswerResponse;
    }

    private String getCandidateAudioAnswerPayload(String questionId, String experience, String answerAudioFileId) {
        AudioSubmitAnswerPayload payload = buildAudioSubmitAnswerPayload(questionId, experience, answerAudioFileId);
        payload.setCandidateAnswer(null);
        return JavaToJSON.convertToJSON(payload);
    }

    private AudioSubmitAnswerPayload buildAudioSubmitAnswerPayload(String questionId, String experience, String answerAudioFileId) {
        AudioSubmitAnswerPayload.QuestionCopies questionCopies = AudioSubmitAnswerPayload.QuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioSubmitAnswerPayload.OptionCopies optionCopies = AudioSubmitAnswerPayload.OptionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioSubmitAnswerPayload.FullQuestionCopies fullQuestionCopies = AudioSubmitAnswerPayload.FullQuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioSubmitAnswerPayload.CopyBreakdown copyBreakdown = AudioSubmitAnswerPayload.CopyBreakdown.builder()
                .questionCopies(questionCopies)
                .optionCopies(optionCopies)
                .fullQuestionCopies(fullQuestionCopies)
                .build();

        AudioSubmitAnswerPayload.CopyPasteAnalysis copyPasteAnalysis = AudioSubmitAnswerPayload.CopyPasteAnalysis.builder()
                .totalDuration(37954)
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
                .copyBreakdown(copyBreakdown)
                .sessionId("copypaste_1766289821664_bd4ahxzy5")
                .analysisVersion("2.0-mcq-focused")
                .timestamp("2025-12-21T04:04:19.618Z")
                .build();

        return AudioSubmitAnswerPayload.builder()
                .questionId(questionId)
                .type("audio")
                .skill("api")
                .jobRoleId(ScreeningControl.jobRoleID)
                .experience(experience)
                .jobApplicationId(ScreeningControl.jobApplicationID)
                .timeSpent(29)
                .fullScreenExitCount(0)
                .tabSwitchCount(0)
                .candidateAnswer(null)
                .copyPasteAnalysis(copyPasteAnalysis)
                .hasCopyPasteAnalysis(true)
                .answerAudioFileId(answerAudioFileId)
                .retakes(0)
                .build();
    }

    private String getAudioAnswerPayload(String fileId, String questionId) {
        AudioAnswerPayload payload = buildAudioAnswerPayload(fileId, questionId);
        String jsonPayload = JavaToJSON.convertToJSON(payload);
        return jsonPayload;
    }

    private AudioAnswerPayload buildAudioAnswerPayload(String fileId, String questionId) {
        AudioAnswerPayload.QuestionCopies questionCopies = AudioAnswerPayload.QuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioAnswerPayload.OptionCopies optionCopies = AudioAnswerPayload.OptionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioAnswerPayload.FullQuestionCopies fullQuestionCopies = AudioAnswerPayload.FullQuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(new ArrayList<>())
                .build();

        AudioAnswerPayload.CopyBreakdown copyBreakdown = AudioAnswerPayload.CopyBreakdown.builder()
                .questionCopies(questionCopies)
                .optionCopies(optionCopies)
                .fullQuestionCopies(fullQuestionCopies)
                .build();

        AudioAnswerPayload.CopyPasteAnalysis copyPasteAnalysis = AudioAnswerPayload.CopyPasteAnalysis.builder()
                .totalDuration(37061)
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
                .copyBreakdown(copyBreakdown)
                .sessionId("copypaste_1766289821664_bd4ahxzy5")
                .analysisVersion("2.0-mcq-focused")
                .timestamp("2025-12-21T04:04:18.725Z")
                .build();

        return AudioAnswerPayload.builder()
                .fileId(fileId)
                .question("<p><span style=\"font-size: 16px\">so assume that you are doing a payment from your phone and it is redirected from your account but a retailer didn't get the any confirmation on their phone so in that case your from your end request has been submitted but that is not executed from the server perspective and not received from the retailer mobile so what kind of status code will return to you in your phone from the server</span></p>")
                .questionId(questionId)
                .candidateScreeningId(ScreeningControl.candidateScreeningId)
                .candidateApplicationId(ScreeningControl.jobApplicationID)
                .skillName("api")
                .type("audio")
                .jobRoleId(ScreeningControl.jobRoleID)
                .maxTime("1")
                .experience("4.0")
                .copyPasteAnalysis(copyPasteAnalysis)
                .hasCopyPasteAnalysis(true)
                .fullScreenExitCount(0)
                .tabSwitchCount(0)
                .build();
    }


    private Response generateAudioURL() {
        AudioFileGeneratorPayload videoFilePayload = AudioFileGeneratorPayload.builder()
                .originalFilename("recordedAudio.webm")
                .fileType("audio")
                .mimeType("audio/webm")
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        String jsonPayload = JavaToJSON.convertToJSON(videoFilePayload);
        setBody(jsonPayload);

        return executePostAPI("/api/candidateScreening/generate-upload-url");
    }

    private Response uploadAudioFileToAzure(String azureURL, String audioFilePath) {
        Path path = Paths.get(audioFilePath);
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

}
