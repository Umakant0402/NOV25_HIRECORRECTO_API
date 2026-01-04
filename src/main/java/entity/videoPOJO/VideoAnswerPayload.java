package entity.videoPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoAnswerPayload {
    public String questionId;
    public String type;
    public String skill;
    public String jobRoleId;
    public String experience;
    public String jobApplicationId;
    public Integer timeSpent;
    public Integer fullScreenExitCount;
    public Integer tabSwitchCount;
    public Object candidateAnswer;
    public CopyPasteAnalysis copyPasteAnalysis;
    public Boolean hasCopyPasteAnalysis;
    public String answerVideoFileId;
    public Integer retakes;


    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CopyBreakdown {
        public QuestionCopies questionCopies;
        public OptionCopies optionCopies;
        public FullQuestionCopies fullQuestionCopies;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CopyPasteAnalysis {
        public Integer totalDuration;
        public Integer totalCopyEvents;
        public Integer questionCopyCount;
        public Integer optionCopyCount;
        public Integer fullQuestionCopyCount;
        public Boolean hasQuestionCopying;
        public Boolean hasOptionCopying;
        public Boolean hasFullQuestionCopying;
        public Integer riskScore;
        public String riskLevel;
        public Boolean isSuspicious;
        public CopyBreakdown copyBreakdown;
        public String sessionId;
        public String analysisVersion;
        public String timestamp;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FullQuestionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OptionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class QuestionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }

}
