import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UrlValidator {

    private static final int TIMEOUT = 5000; // 5秒超时

    /**
     * 检查URL是否可访问
     */
    public static UrlCheckResult checkUrl(String urlString) {
        return checkUrl(urlString, TIMEOUT);
    }

    /**
     * 检查URL是否可访问（带超时设置）
     */
    public static UrlCheckResult checkUrl(String urlString, int timeoutMs) {
        long startTime = System.currentTimeMillis();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(timeoutMs);
            connection.setReadTimeout(timeoutMs);
            connection.setInstanceFollowRedirects(true);

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            long responseTime = System.currentTimeMillis() - startTime;

            return new UrlCheckResult(
                    responseCode >= 200 && responseCode < 400,
                    responseCode,
                    responseMessage,
                    responseTime,
                    null
            );

        } catch (Exception e) {
            return new UrlCheckResult(
                    false,
                    -1,
                    e.getMessage(),
                    System.currentTimeMillis() - startTime,
                    e.getClass().getSimpleName()
            );
        }
    }

    /**
     * 异步检查URL（防止阻塞主线程）
     */
    public static Future<UrlCheckResult> checkUrlAsync(String urlString) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(new Callable<UrlCheckResult>() {
            @Override
            public UrlCheckResult call() {
                return checkUrl(urlString);
            }
        });
    }

    /**
     * 带重试机制的URL检查
     */
    public static UrlCheckResult checkUrlWithRetry(String urlString, int retryCount) {
        for (int i = 0; i < retryCount; i++) {
            UrlCheckResult result = checkUrl(urlString);
            if (result.isAccessible()) {
                return result;
            }

            if (i < retryCount - 1) {
                try {
                    Thread.sleep(1000); // 重试间隔1秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        return checkUrl(urlString); // 最后一次尝试
    }

    // 结果封装类
    public static class UrlCheckResult {
        private boolean accessible;
        private int responseCode;
        private String responseMessage;
        private long responseTime;
        private String errorType;

        public UrlCheckResult(boolean accessible, int responseCode, String responseMessage,
                              long responseTime, String errorType) {
            this.accessible = accessible;
            this.responseCode = responseCode;
            this.responseMessage = responseMessage;
            this.responseTime = responseTime;
            this.errorType = errorType;
        }

        // getters
        public boolean isAccessible() { return accessible; }
        public int getResponseCode() { return responseCode; }
        public String getResponseMessage() { return responseMessage; }
        public long getResponseTime() { return responseTime; }
        public String getErrorType() { return errorType; }

        @Override
        public String toString() {
            if (accessible) {
                return String.format("✅ 可访问 | 状态码: %d | 响应时间: %dms",
                        responseCode, responseTime);
            } else {
                return String.format("❌ 不可访问 | 错误: %s | 详情: %s",
                        errorType, responseMessage);
            }
        }
    }

    // 测试使用
    public static void main(String[] args) {
        String testUrl = "http://tvbox.王二小放牛娃.top/";

        // 简单检查
        UrlCheckResult result = UrlValidator.checkUrl(testUrl);
        System.out.println("检查结果: " + result);

        // 带重试的检查
        UrlCheckResult retryResult = UrlValidator.checkUrlWithRetry(testUrl, 3);
        System.out.println("重试检查结果: " + retryResult);

        // 异步检查
        Future<UrlCheckResult> future = UrlValidator.checkUrlAsync(testUrl);
        try {
            UrlCheckResult asyncResult = future.get(10, TimeUnit.SECONDS);
            System.out.println("异步检查结果: " + asyncResult);
        } catch (Exception e) {
            System.out.println("异步检查超时或出错");
        }
    }
}