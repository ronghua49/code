package com.swiftpass.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2018-2-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SwiftpassConfig {
    /*测试版*/
    /**
     * 交易密钥
     */
    //public static String key = "e5abd0511ebb326007ade7567f1a03e5";
    
    /**RSA签名私钥*/
    //public static String mchPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfU8v4BUr81SKm/H0ahbdQZjEpO8nMyk+xuYSatHwnU4//m47R+4G2YB4Z6PHsJi4+ScfJpQutFhKrFwTXZ6TDqLvaqZDDkJq5G271g+PmrzFp7f40/E9m0qjeL64RJra0rZql23dvPW4vVomMRgRcoPOn0YWVp+M6T5PaFgE4M8dh4lMZz57gVwOdd08F99Z92f3QgZtEjI+/EXvMenXxb/aRofNkt+Wdk2ELJ6MIP0d9UU5v3WgLuuNv5QnQYzj/RMr8GD+wrDYiNQJxsaTmE/OEJggsumhD4eYY5YlRy2EIN504cujYVKU1wOSZgq9oJCynGR0aPuQWx58IHxEtAgMBAAECggEAHfEFd8qm2PTE2lTAvec7F+TcgD84IUAz0dZnURtx6YIOoZ5+LH/zVG6juYLJU/Oo5RPAc+iMVS68u2JMCp7zm8Ft7B3JkrbuHLNHGuR6Q7PQuXN8PkDcOxqDmZ2kPJzl4PZvBZRE0abdug+tMatGzpGAuJzrWcB/N0oVIvrXp9PnOqfo/Y5nxmpOFCImJppIS3AL1pftNtQZo9G15CPHDYtpUbXPtD2MjjW4OLxKuPRoHSwUgo6LW9XSwNXfcuK+lbzLL0BhlWD9IV/+yCEUEblN87yxxfhpQFaAhXj5W+B3YsMOZuK93+XMOpYmw8EpUDMObOnvwb0NSHUrV2RUAQKBgQDTojlnNS1e7+tjPzFtOhGPj1uCBPAEIeHAcnPgd80bEiujxMLCnGaAvmnTrMu4Xo0e5fAP4F7R6UD+IUsfr3CAAu7CadQ49TW+SovAvciy9AZuSVVIwynu6QdYgFyPKe1LZYAEq5k+mB1Vh5q0RoxMNAA5pGYKg8+4MmmsJi7X7QKBgQDAunCOqIiH128bs/1VRIhDpzuRW5Qr/SRbO2saVg5RSHnO/nGT2OuxSTTkc8yrx7qd9SmAxXl5kR238DhMOQOnRBomldmVtAJuJgrdQyt0wXfeQVQqshqCUaE/xhEbpSCdbPSZbKZZdplV0y6O5vXIhxw+1qAvXLcxw46s3R92QQKBgQClQ+ejywkVPDILHMwSSehwvThufkCYWYUbbcVDowpOe5AMoZidtNju7MNjg2rLHTsCx/kBzOr+7THNwl4R7kTiEmg09cO+fu5rHXepGgtig+GJukaZPZ6/bMZJvGOLgOhHmomwG/jdwpgVtIGBCh6BW5JZcSImT+ykIOoYfvDRuQKBgCgwOHxnBGFfORoLxE3dhpSk8LT05cbueIBVuZW6UC3+8PeK82AjIbLMUy04QHupoG6Dyu3BP/1rl0jd3L94PBzLBLD7Gm4vJTqW0DknYo5sMXS1JrnofcKjBv7nbHXZTx3EtJSxpVaOdpcA/HpsCuCP3AH2e1yk9sZ3wu6lBYSBAoGACYM60j1CVRNSZxUNRgiwfWzS69qI1eezPc7xQEganpVBI9SZcTNp1kpDKmQikXJ4Yb5XWn12HCY/sFeBW6Su3ruNqxvg1XiUPbH6A6nxd5B3QX0mS9+wDm6ONysPLRdKbfFO0mdP4CeyuGPdvDIMXP4dJdLhMUL4pcJLI0B7gBE=";
    /**RSA签名平台公钥*/
    //public static String platPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqNxzebovJ6R+LF0jFyJD4vgdvj+Apmb5h+pW3T0EtDzWZAr7tyiSAtNedYvRjJCqN5cYw0rIwGMZFbD3lQHbJGC+IvpqXwPB8AWqRAwItI82fo2+AyHkq11yE27IgOjSrKofgg3GWJ6SSQonYuXZ0c09chXXiZPKYe0zRbvq83kAVsYDu1sMwi8mfiVff6CIALsehs1MOjmdLW40N1CicVmJaWuh2yee+sj1/0xMOlV1LyJq63hShBD7T93qpGbHoNkpdz+BFc2byrhv1idbB4DRbUiKynzj3FX2Nz8Dv9TFQv8p2Z8dIOst890atv3P8DO7a9FI8I1reLvFDdyPawIDAQAB";
    
    /**
     * 商户号
     */
    //public static String mch_id = "102532336411";
    
    /**
     * 支付接口请求url
     */
    //public static String req_url = "https://pay.swiftpass.cn/pay/gateway";
    
	
    /**
     * 交易密钥
     */
    public static String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvJaRXF2bKBPLIASEFay8bvjRx7U/gF2tmbvolomRTBw4mH4ZYYPJT5RSiRy1VdRMOKnFl5Ld8xJyhQw4MIP+0ica4I98RaV88eEJwfeTOUf12C4SKg+T63nTYBahyTHJiYKK7B+TnWHYsI3gGnYy/mztB9raDcmRuzxP0bObvgJy0ovibAuyQAWAI706+tw9H1pNTzyJpM+loJFkf5PvsC7TwMQWmWOH21VUvLIJL9ilbegk6+V4eR9Y5HPKvcTksFlFF0AJTVElfTaSdgqCWLcFbaX/hnFLifv37OFftupBNnYZxKCnXFU3r6sNf5hyCP7kTMg6A/Tb982/VYCRHQIDAQAB";
    
    /**RSA签名私钥*//*待提供*/
    public static String mchPrivateKey = "MIIEpQIBAAKCAQEAvJaRXF2bKBPLIASEFay8bvjRx7U/gF2tmbvolomRTBw4mH4ZYYPJT5RSiRy1VdRMOKnFl5Ld8xJyhQw4MIP+0ica4I98RaV88eEJwfeTOUf12C4SKg+T63nTYBahyTHJiYKK7B+TnWHYsI3gGnYy/mztB9raDcmRuzxP0bObvgJy0ovibAuyQAWAI706+tw9H1pNTzyJpM+loJFkf5PvsC7TwMQWmWOH21VUvLIJL9ilbegk6+V4eR9Y5HPKvcTksFlFF0AJTVElfTaSdgqCWLcFbaX/hnFLifv37OFftupBNnYZxKCnXFU3r6sNf5hyCP7kTMg6A/Tb982/VYCRHQIDAQABAoIBABjeOfjWFxmn0oc8UTaSep6fjPoqul7w+21kS/1d0p0Ys5XYOvyS5j7qLiAKexcAUbS/HmEoasAc1KEaxWYQYc/QfEjUYuzEL7+tjx3WycEp7g89tAN73mOUwvzOvzLKr+rEHhGY8Fw2hN5zNvQGVh8auf7pKwvaYq8v+s6KQqVBbzOMwaQltdPlC8EXOXeN5CnTd6tI9U9fwRmK4T2ERSvE7aafS2n+Qc0P8RPC9knx4fbsZyKzd1jvdqfeOQBxYPyInczVCEFxHRDu30d+VVFXgvFgLSA5U11yGtV0aTsKmG1XiVYUum0MYZtvgXTXBso/lPLLKMdwhvFUZetKNMECgYEA8SttGO6xl7hc2KJ9guYnYbqIZGGEtG5CRtCrSixSzUchV1d7SrA+wFJuPRaENk0gG+oyM0g5EIflUyNp7ltPQ+oR3JpR6NpiW3WI3HgnZLFNd79ifAdofienuwL5C7mkL/7mvtkZ74vdrkSBItK2uI6b/FPVmF+PrkV74O5Da2UCgYEAyC9i4liUZui8gxCYGM5IU9kwa+XwN40E6fEEiCea3vzeDCd+aaS5jRRf1Ka2Bx0DbRyr/SjW8WFLkj67RohssugAXORoUW5Ujteh2wJmvEOPG09/N+MWNDYlx/nd8JdvjgybqHvYsudP0qbKgURq45Ba/bRWl1/8t5/azrZjH1kCgYEAzeTkREfsSHp2keDPxMRXVOEadpCm6h/D549Y3X9Pxb93YIjoUpB7UlhNk0u43l8kDZD5AL0lMs7CYyM12fAMkQ+x5iO90E05cZm7mXXfM+EHITe8jeUJOcMDOO7FzlPUL8GwH7lboh5HyPLlSOSaaKAf3eF41pinIwJhS1w/JN0CgYEAvIj8jYa1hdJuMNZBGSO2tNmZpBU0T21OEnJ/PShn0NnA2xubEC4m3zswIhw81ag6oNnfFZgQD9f9j6frQzubfEfKh7JKmV7a3okxlA3H6kmKj0Mo4t7J+7R/kr6+iyhk1RkExQKXDa+S2ydhE6v3K4m6WLUu1HR/lAVG02atJkkCgYEAsEhqXVQ4TmfQ6L/AbTFTA0NStxzxEVne9zrKnwtuu0iVPtGdBkz/gym1U0eLAG+yhMPIjplL/35j2eRtXLV9rbAC47jIrGnM0q6VwlleGK/VHExp6+4Veeq/7JovQoltY+6S4R7ixd7RnPRwXRR7eD+MM9Wk4StxSR5EyjPI1X0=";
    /**RSA签名平台公钥*/
    public static String platPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqNxzebovJ6R+LF0jFyJD4vgdvj+Apmb5h+pW3T0EtDzWZAr7tyiSAtNedYvRjJCqN5cYw0rIwGMZFbD3lQHbJGC+IvpqXwPB8AWqRAwItI82fo2+AyHkq11yE27IgOjSrKofgg3GWJ6SSQonYuXZ0c09chXXiZPKYe0zRbvq83kAVsYDu1sMwi8mfiVff6CIALsehs1MOjmdLW40N1CicVmJaWuh2yee+sj1/0xMOlV1LyJq63hShBD7T93qpGbHoNkpdz+BFc2byrhv1idbB4DRbUiKynzj3FX2Nz8Dv9TFQv8p2Z8dIOst890atv3P8DO7a9FI8I1reLvFDdyPawIDAQAB";
    
    /**
     * 商户号
     */
    public static String mch_id = "102536452751";
    
    /**
     * 支付接口请求url
     */
    public static String req_url = "https://pay.swiftpass.cn/pay/gateway";
    
    
}
