package entity.videoPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoAnswerAnalysisPayload {
    public String fileId;
    public String question;
    public String questionId;
    public String candidateScreeningId;
    public String candidateApplicationId;
    public String skillName;
    public String type;
    public String jobRoleId;
    public String maxTime;
    public String experience;
    public CopyPasteAnalysis copyPasteAnalysis;
    public Boolean hasCopyPasteAnalysis;
    public Integer fullScreenExitCount;
    public Integer tabSwitchCount;

    @Builder
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CopyBreakdown {
        public QuestionCopies questionCopies;
        public OptionCopies optionCopies;
        public FullQuestionCopies fullQuestionCopies;
    }

    @Builder
    @Setter
    @Getter
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
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FullQuestionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }

    @Builder
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OptionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }

    @Builder
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class QuestionCopies {
        public Integer count;
        public Integer averageLength;
        public List<Object> timestamps;
    }
}