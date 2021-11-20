package com.bookiply.interview.assignment.dtos;

public class FirehoseDto {

    private Long totalFirehosesLength;
    private HydrantDto[] hydrants;

    private FirehoseDto(Long totalFirehosesLength, HydrantDto[] hydrants)
    {
        this.totalFirehosesLength = totalFirehosesLength;
        this.hydrants = hydrants;
    }

    public Long getTotalFirehosesLength() {
        return totalFirehosesLength;
    }

    public HydrantDto[] getHydrants() {
        return hydrants;
    }

    public static FirehoseDtoBuilder newBuilder()
    {
        return new FirehoseDtoBuilder();
    }

    public static class FirehoseDtoBuilder
    {
        private Long totalFirehosesLength;
        private HydrantDto[] hydrants;


        public FirehoseDtoBuilder setTotalFirehosesLength(Long totalFirehosesLength)
        {
            this.totalFirehosesLength = totalFirehosesLength;
            return this;
        }

        public FirehoseDtoBuilder setHydrants(HydrantDto[] hydrants)
        {
            this.hydrants = hydrants;
            return this;
        }

        public FirehoseDto createFirehoseDto()
        {
            return new FirehoseDto(totalFirehosesLength, hydrants);
        }
    }
}
