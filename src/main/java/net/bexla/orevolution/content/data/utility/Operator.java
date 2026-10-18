package net.bexla.orevolution.content.data.utility;

public enum Operator {
    ADD {
        @Override
        public float apply(float value, float modifier) {
            return value + modifier;
        }

        @Override
        public Object format(float modifier) {
            return "+" + (int) modifier;
        }
    },

    SUBTRACT {
        @Override
        public float apply(float value, float modifier) {
            return value - modifier;
        }

        @Override
        public Object format(float modifier) {
            return "-" + (int) modifier;
        }
    },

    MULTIPLY_FORMAT_PERCENT {
        @Override
        public float apply(float value, float modifier) {
            return value * modifier;
        }

        @Override
        public Object format(float modifier) {
            return "+" + (int) ((modifier - 1) * 100) + "%";
        }
    },

    DIVIDE {
        @Override
        public float apply(float value, float modifier) {
            return value / modifier;
        }

        @Override
        public Object format(float modifier) {
            return "-" + (int) ((1 - modifier) * 100) + "%";
        }
    },

    MULTIPLY_FORMAT_X {
        @Override
        public float apply(float value, float modifier) {
            return value * modifier;
        }

        @Override
        public Object format(float modifier) {
            return modifier + "x";
        }
    };

    public abstract float apply(float value, float modifier);

    public abstract Object format(float modifier);
}