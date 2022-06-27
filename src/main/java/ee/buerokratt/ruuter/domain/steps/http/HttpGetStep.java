package ee.buerokratt.ruuter.domain.steps.http;

import com.fasterxml.jackson.databind.JsonNode;
import ee.buerokratt.ruuter.domain.ConfigurationInstance;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.http.HttpResponse;

import static ee.buerokratt.ruuter.util.HttpUtils.makeHttpGetRequest;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class HttpGetStep extends HttpStep {
    @Override
    protected void executeStepAction(ConfigurationInstance ci) {
        HttpResponse<String> response = makeHttpGetRequest(args);
        JsonNode responseBody = response.body().isEmpty() ? null : ci.getMappingHelper().convertStringToNode(response.body());
        HttpQueryResponse httpQueryResponse = new HttpQueryResponse(responseBody, response.headers().map(), response.statusCode());
        ci.getContext().put(resultName, new HttpStepResult(args, httpQueryResponse));
    }

    @Override
    public String getType() {
        return "http.get";
    }
}
