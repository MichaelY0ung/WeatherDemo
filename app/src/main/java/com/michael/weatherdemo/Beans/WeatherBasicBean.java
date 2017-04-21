package com.michael.weatherdemo.Beans;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/5.
 */

public class WeatherBasicBean {
    /**
     * status : OK
     * weather : [{"city_name":"上海","city_id":"CHSH000000","last_update":"2017-04-05T15:02:00+08:00","now":{"text":"小雨","code":"13","temperature":"23","feels_like":"23","wind_direction":"东","wind_speed":"2.52","wind_scale":"1","humidity":"56","visibility":"16.10","pressure":"1010","pressure_rising":"未知","air_quality":{"city":{"aqi":"58","pm25":"40","pm10":"65","so2":"13","no2":"46","co":"0.770","o3":"106","last_update":"2017-04-05T14:00:00+08:00","quality":"良"},"stations":null},"alarms":[]},"today":{"sunrise":"05:39 AM","sunset":"06:16 PM","suggestion":{"dressing":{"brief":"较舒适","details":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"uv":{"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},"car_washing":{"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"travel":{"brief":"适宜","details":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"},"flu":{"brief":"易发","details":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。"}}},"future":[{"date":"2017-04-05","high":"22","low":"16","day":"周三","text":"小雨/小雨","code1":"13","code2":"13","cop":"","wind":"风力2级"},{"date":"2017-04-06","high":"23","low":"15","day":"周四","text":"中雨/中雨","code1":"14","code2":"14","cop":"","wind":"风力2级"},{"date":"2017-04-07","high":"20","low":"14","day":"周五","text":"阵雨/多云","code1":"10","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-04-08","high":"24","low":"13","day":"周六","text":"多云/小雨","code1":"4","code2":"13","cop":"","wind":"风力2级"},{"date":"2017-04-09","high":"18","low":"11","day":"周日","text":"小雨/阵雨","code1":"13","code2":"10","cop":"","wind":"风力2级"},{"date":"2017-04-10","high":"16","low":"11","day":"周一","text":"阵雨/多云","code1":"10","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-04-11","high":"18","low":"12","day":"周二","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力2级"},{"date":"2017-04-12","high":"16","low":"9","day":"周三","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力4级"},{"date":"2017-04-13","high":"16","low":"10","day":"周四","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力3级"},{"date":"2017-04-14","high":"16","low":"11","day":"周五","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力4级"}]}]
     */

    private String status;
    private ArrayList<WeatherBean> weather;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<WeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherBean> weather) {
        this.weather = weather;
    }

    public class WeatherBean {
        /**
         * city_name : 上海
         * city_id : CHSH000000
         * last_update : 2017-04-05T15:02:00+08:00
         * now : {"text":"小雨","code":"13","temperature":"23","feels_like":"23","wind_direction":"东","wind_speed":"2.52","wind_scale":"1","humidity":"56","visibility":"16.10","pressure":"1010","pressure_rising":"未知","air_quality":{"city":{"aqi":"58","pm25":"40","pm10":"65","so2":"13","no2":"46","co":"0.770","o3":"106","last_update":"2017-04-05T14:00:00+08:00","quality":"良"},"stations":null},"alarms":[]}
         * today : {"sunrise":"05:39 AM","sunset":"06:16 PM","suggestion":{"dressing":{"brief":"较舒适","details":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"uv":{"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},"car_washing":{"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"travel":{"brief":"适宜","details":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"},"flu":{"brief":"易发","details":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。"}}}
         * future : [{"date":"2017-04-05","high":"22","low":"16","day":"周三","text":"小雨/小雨","code1":"13","code2":"13","cop":"","wind":"风力2级"},{"date":"2017-04-06","high":"23","low":"15","day":"周四","text":"中雨/中雨","code1":"14","code2":"14","cop":"","wind":"风力2级"},{"date":"2017-04-07","high":"20","low":"14","day":"周五","text":"阵雨/多云","code1":"10","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-04-08","high":"24","low":"13","day":"周六","text":"多云/小雨","code1":"4","code2":"13","cop":"","wind":"风力2级"},{"date":"2017-04-09","high":"18","low":"11","day":"周日","text":"小雨/阵雨","code1":"13","code2":"10","cop":"","wind":"风力2级"},{"date":"2017-04-10","high":"16","low":"11","day":"周一","text":"阵雨/多云","code1":"10","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-04-11","high":"18","low":"12","day":"周二","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力2级"},{"date":"2017-04-12","high":"16","low":"9","day":"周三","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力4级"},{"date":"2017-04-13","high":"16","low":"10","day":"周四","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力3级"},{"date":"2017-04-14","high":"16","low":"11","day":"周五","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力4级"}]
         */

        private String city_name;
        private String city_id;
        private String last_update;
        private NowBean now;
        private TodayBean today;
        private ArrayList<FutureBean> future;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public ArrayList<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(ArrayList<FutureBean> future) {
            this.future = future;
        }

        public class NowBean {
            /**
             * text : 小雨
             * code : 13
             * temperature : 23
             * feels_like : 23
             * wind_direction : 东
             * wind_speed : 2.52
             * wind_scale : 1
             * humidity : 56
             * visibility : 16.10
             * pressure : 1010
             * pressure_rising : 未知
             * air_quality : {"city":{"aqi":"58","pm25":"40","pm10":"65","so2":"13","no2":"46","co":"0.770","o3":"106","last_update":"2017-04-05T14:00:00+08:00","quality":"良"},"stations":null}
             * alarms : []
             */

            private String text;
            private String code;
            private String temperature;
            private String feels_like;
            private String wind_direction;
            private String wind_speed;
            private String wind_scale;
            private String humidity;
            private String visibility;
            private String pressure;
            private String pressure_rising;
            private AirQualityBean air_quality;
            private ArrayList<?> alarms;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getFeels_like() {
                return feels_like;
            }

            public void setFeels_like(String feels_like) {
                this.feels_like = feels_like;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getPressure() {
                return pressure;
            }

            public void setPressure(String pressure) {
                this.pressure = pressure;
            }

            public String getPressure_rising() {
                return pressure_rising;
            }

            public void setPressure_rising(String pressure_rising) {
                this.pressure_rising = pressure_rising;
            }

            public AirQualityBean getAir_quality() {
                return air_quality;
            }

            public void setAir_quality(AirQualityBean air_quality) {
                this.air_quality = air_quality;
            }

            public ArrayList<?> getAlarms() {
                return alarms;
            }

            public void setAlarms(ArrayList<?> alarms) {
                this.alarms = alarms;
            }

            public class AirQualityBean {
                /**
                 * city : {"aqi":"58","pm25":"40","pm10":"65","so2":"13","no2":"46","co":"0.770","o3":"106","last_update":"2017-04-05T14:00:00+08:00","quality":"良"}
                 * stations : null
                 */

                private CityBean city;
                private Object stations;

                public CityBean getCity() {
                    return city;
                }

                public void setCity(CityBean city) {
                    this.city = city;
                }

                public Object getStations() {
                    return stations;
                }

                public void setStations(Object stations) {
                    this.stations = stations;
                }

                public class CityBean {
                    /**
                     * aqi : 58
                     * pm25 : 40
                     * pm10 : 65
                     * so2 : 13
                     * no2 : 46
                     * co : 0.770
                     * o3 : 106
                     * last_update : 2017-04-05T14:00:00+08:00
                     * quality : 良
                     */

                    private String aqi;
                    private String pm25;
                    private String pm10;
                    private String so2;
                    private String no2;
                    private String co;
                    private String o3;
                    private String last_update;
                    private String quality;

                    public String getAqi() {
                        return aqi;
                    }

                    public void setAqi(String aqi) {
                        this.aqi = aqi;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public String getSo2() {
                        return so2;
                    }

                    public void setSo2(String so2) {
                        this.so2 = so2;
                    }

                    public String getNo2() {
                        return no2;
                    }

                    public void setNo2(String no2) {
                        this.no2 = no2;
                    }

                    public String getCo() {
                        return co;
                    }

                    public void setCo(String co) {
                        this.co = co;
                    }

                    public String getO3() {
                        return o3;
                    }

                    public void setO3(String o3) {
                        this.o3 = o3;
                    }

                    public String getLast_update() {
                        return last_update;
                    }

                    public void setLast_update(String last_update) {
                        this.last_update = last_update;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }
                }
            }
        }

        public class TodayBean {
            /**
             * sunrise : 05:39 AM
             * sunset : 06:16 PM
             * suggestion : {"dressing":{"brief":"较舒适","details":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"uv":{"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},"car_washing":{"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"travel":{"brief":"适宜","details":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"},"flu":{"brief":"易发","details":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。"}}
             */

            private String sunrise;
            private String sunset;
            private SuggestionBean suggestion;

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public SuggestionBean getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(SuggestionBean suggestion) {
                this.suggestion = suggestion;
            }

            public class SuggestionBean {
                /**
                 * dressing : {"brief":"较舒适","details":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
                 * uv : {"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
                 * car_washing : {"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
                 * travel : {"brief":"适宜","details":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"}
                 * flu : {"brief":"易发","details":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"}
                 * sport : {"brief":"较不宜","details":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。"}
                 */

                private DressingBean dressing;
                private UvBean uv;
                private CarWashingBean car_washing;
                private TravelBean travel;
                private FluBean flu;
                private SportBean sport;

                public DressingBean getDressing() {
                    return dressing;
                }

                public void setDressing(DressingBean dressing) {
                    this.dressing = dressing;
                }

                public UvBean getUv() {
                    return uv;
                }

                public void setUv(UvBean uv) {
                    this.uv = uv;
                }

                public CarWashingBean getCar_washing() {
                    return car_washing;
                }

                public void setCar_washing(CarWashingBean car_washing) {
                    this.car_washing = car_washing;
                }

                public TravelBean getTravel() {
                    return travel;
                }

                public void setTravel(TravelBean travel) {
                    this.travel = travel;
                }

                public FluBean getFlu() {
                    return flu;
                }

                public void setFlu(FluBean flu) {
                    this.flu = flu;
                }

                public SportBean getSport() {
                    return sport;
                }

                public void setSport(SportBean sport) {
                    this.sport = sport;
                }

                public class DressingBean {
                    /**
                     * brief : 较舒适
                     * details : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }

                public class UvBean {
                    /**
                     * brief : 最弱
                     * details : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }

                public class CarWashingBean {
                    /**
                     * brief : 不宜
                     * details : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }

                public class TravelBean {
                    /**
                     * brief : 适宜
                     * details : 温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }

                public class FluBean {
                    /**
                     * brief : 易发
                     * details : 天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }

                public class SportBean {
                    /**
                     * brief : 较不宜
                     * details : 有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }
                }
            }
        }

        public class FutureBean {
            /**
             * date : 2017-04-05
             * high : 22
             * low : 16
             * day : 周三
             * text : 小雨/小雨
             * code1 : 13
             * code2 : 13
             * cop :
             * wind : 风力2级
             */

            private String date;
            private String high;
            private String low;
            private String day;
            private String text;
            private String code1;
            private String code2;
            private String cop;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode1() {
                return code1;
            }

            public void setCode1(String code1) {
                this.code1 = code1;
            }

            public String getCode2() {
                return code2;
            }

            public void setCode2(String code2) {
                this.code2 = code2;
            }

            public String getCop() {
                return cop;
            }

            public void setCop(String cop) {
                this.cop = cop;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
        }
    }
}
