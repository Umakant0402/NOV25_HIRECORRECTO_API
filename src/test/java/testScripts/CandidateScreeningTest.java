package testScripts;

import base.ScreeningControl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static base.ScreeningControl.jobRoleID;

public class CandidateScreeningTest {

    @Test
    public void candidateMCQScreeningTest() {

        ScreeningControl.candidateScreeningId = "694770d452e582fefe7e1ebc";
        String experience = "4.0";
        ScreeningControl.jobRoleID = "68943b744df518afa9442034";
        ScreeningControl.jobApplicationID = "69476ed0c41f0cc9ce8110f0";
        String screenTestID = "692d3aeb3d9838750c2d650a";

        ScreeningDetailsServices screeningDetailsServices = new ScreeningDetailsServices();
        Response screeningDetailsResponse = screeningDetailsServices.getScreeningDetails(screenTestID);

        String mcqQuestionID = screeningDetailsResponse.jsonPath().getString("find { it.mcq != null }.mcq[0]._id");
        String audioQuestionID = screeningDetailsResponse.jsonPath().getString("find { it.audio != null }.audio[0]._id");
        String videoQuestionID = screeningDetailsResponse.jsonPath().getString("find { it.video != null }.video[0]._id");
        String subjectiveQuestionID = screeningDetailsResponse.jsonPath().getString("find { it.subjective != null }.subjective[0]._id");
        String programmingQuestionID = screeningDetailsResponse.jsonPath().getString("find { it.programming != null }.programming[0]._id");

//        String mcq = screeningDetailsResponse.jsonPath().getString("[0].mcq[0]._id");
//        String audio = screeningDetailsResponse.jsonPath().getString("[0].audio[0]._id");


        List<String> questionIds = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        answers.add("SignatureException");

        questionIds = screeningDetailsResponse.jsonPath().getList("find { it.mcq != null }.mcq._id");


        String audioPath = "src" + File.separator + "test" + File.separator + "resources"
                + File.separator + "testData" + File.separator + "Media"
                + File.separator + "AudioAnswer.mp3";
        AudioService audioService = new AudioService();
        Response audioAnswerResponse = audioService.submitAudioAnswer(audioQuestionID, experience, audioPath);
        Assert.assertEquals(audioAnswerResponse.statusCode(), 200, "Expected HTTP 200 when submitting Audio answer");

        SubjectiveService subjectiveService = new SubjectiveService();
        Response subjectiveAsnwerResponse = subjectiveService.submitSubjectiveAnswer(subjectiveQuestionID, experience, "<p>- More Reliable</p><p>- More Secure</p><p>- Flackness is lesser then UI</p><p>- Cost Effiective Early Bugs identification</p>");
        Assert.assertEquals(subjectiveAsnwerResponse.statusCode(), 200, "Expected HTTP 200 when submitting Subjective answer");

        String videoPath = "src" + File.separator + "test" + File.separator + "resources"
                + File.separator + "testData" + File.separator + "Media"
                + File.separator + "VideoAnswer.mp4";
        VideoServices videoServices = new VideoServices();
        Response videoAnswerResponse = videoServices.submitVideoAnswer(videoQuestionID, experience, videoPath);
        Assert.assertEquals(videoAnswerResponse.statusCode(), 200, "Expected HTTP 200 when submitting Audio answer");

        for (int i = 0; i < questionIds.size(); i++) {
            MCQService mcqService = new MCQService();
            Response submitMCQAnswerResponse = mcqService.submitMCQAnswer(questionIds.get(i), experience, answers.get(i));
            Assert.assertEquals(submitMCQAnswerResponse.statusCode(), 200, "Expected HTTP 200 when submitting MCQ answer");
        }

//        ProgrammingService programmingService = new ProgrammingService();
//        Response programmingResponse = programmingService.submitProgrammingAnswer("694770d452e582fefe7e1ebc","692d3aee3d9838750c2d6524","68943b744df518afa9442034","4.0","69476ed0c41f0cc9ce8110f0","694770d452e582fefe7e1ebc");
//        Assert.assertEquals(programmingResponse.statusCode(), 200, "Expected HTTP 200 when submitting Audio answer");
    }

}
