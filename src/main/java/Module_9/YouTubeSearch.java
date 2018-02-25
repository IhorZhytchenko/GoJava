package Module_9;

import Module_9.ChannelEntity.ResponseChannel;
import Module_9.Entity.ResponseSearch;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDateTime;

public class YouTubeSearch {
    public static final String API_KEY = "AIzaSyCEFEMFYZfkHX4NlozondtA6a2unDc21zE";
    public static final int SIMPLE_SEACH_RESULT=5;
    public static final int CHANNEL_SEACH_RESULT=10;

    public static final ObjectMapper mapper = new ObjectMapper() {
        public <T> T readValue(String value, Class<T> valueType) {
            return JSON.parseObject(value, valueType);
        }

        public String writeValue(Object value) {
            return JSON.toJSONString(value);
        }
    };
    static {
        Unirest.setObjectMapper(mapper);
    }

    public static ResponseSearch simpleSearch(String query) throws UnirestException {
        HttpResponse<ResponseSearch> response = Unirest.get("https://www.googleapis.com/youtube/v3/search")
                .queryString("key", API_KEY)
                .queryString("part", "snippet")
                .queryString("maxResults", SIMPLE_SEACH_RESULT)
                .queryString("q", query)
                .asObject(ResponseSearch.class);

        return response.getBody();

    }
    public static ResponseSearch advancedSearch(String query,int maxResult,int days) throws UnirestException {
        LocalDateTime dateTime =LocalDateTime.now().minusDays(days);
        String date = dateTime.toString().substring(0,19)+"Z";
        HttpResponse<ResponseSearch> response = Unirest.get("https://www.googleapis.com/youtube/v3/search")
                .queryString("key", API_KEY)
                .queryString("publishedAfter", date)
                .queryString("part", "snippet")
                .queryString("maxResults", maxResult)
                .queryString("q", query)
                .asObject(ResponseSearch.class);

        return response.getBody();

    }
    public static ResponseChannel channelSearch(String id) throws UnirestException {

        HttpResponse<ResponseChannel> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                .queryString("key", API_KEY)
                .queryString("id", id)
                .queryString("part", "snippet")
                .asObject(ResponseChannel.class);

        return response.getBody();


    }
    public static ResponseSearch SearchFromChannel(String id) throws UnirestException {
        HttpResponse<ResponseSearch> response = Unirest.get("https://www.googleapis.com/youtube/v3/search")
                .queryString("key", API_KEY)
                .queryString("part", "snippet")
                .queryString("maxResults", CHANNEL_SEACH_RESULT)
                .queryString("channelId", id)
                .queryString("order", "date")
                .queryString("type", "video")
                .asObject(ResponseSearch.class);

        return response.getBody();
    }
}
