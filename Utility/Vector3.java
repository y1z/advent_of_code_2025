package Utility;

public final class Vector3
{
    /// CONSTANTS
    public final static Vector3 zero = new Vector3(0, 0, 0);
    public final static Vector3 one = new Vector3(1, 1, 1);

    /// VARIABLES
    public float x;
    public float y;
    public float z;

    public Vector3(float _x, float _y, float _z)
    {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    public Vector3(Vector3 other)
    {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public float mag()
    {
        return (float)Math.sqrt(mag_square());
    }

    public float mag_square()
    {
        return (x * x) + (y * y) + (z * z);
    }

    public float length()
    {
        return mag();
    }

    public float length_sqaure()
    {
        return mag_square();
    }

    public float distance_squared(final Vector3 other)
    {
        final float x_diff = this.x - other.x;
        final float y_diff = this.y - other.y;
        final float z_diff = this.z - other.z;
        return (x_diff * x_diff) + (y_diff * y_diff) + (z_diff * z_diff);
    }

    public float distance(final Vector3 other)
    {
        return (float)Math.sqrt(distance_squared(other));
    }

    /**
     *
     * @param other the vector to add to the current one (self)
     * @return a new vector
     */
    public Vector3 add(final Vector3 other)
    {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     *
     * @param other the values to add to the current vector
     */
    public void add_inplace(final Vector3 other)
    {
        this.x = this.x + other.x;
        this.y = this.y + other.y;
        this.z = this.z + other.z;
    }

    /**
     *
     * @param other the vector to subtract from the current one (self)
     * @return a new vector
     */
    public Vector3 sub(final Vector3 other)
    {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     *
     * @param other the values to subtract from the current vector
     */
    public void sub_inplace(final Vector3 other)
    {
        this.x = this.x - other.x;
        this.y = this.y - other.y;
        this.z = this.z - other.z;
    }

    /**
     * 
     * @param multipiler how much each element of the vector is multipiled by
     * @return a new vector
     */
    public Vector3 mul(final float multipiler)
    {
        return new Vector3(this.x * multipiler, this.y * multipiler, this.z * multipiler);
    }

    /**
     *  Multipiles the current vector by a given amount
     * @param multipiler
     */
    public void mul_inplace(final float multipiler)
    {
        this.x = this.x * multipiler;
        this.y = this.y * multipiler;
        this.z = this.z * multipiler;
    }
}
