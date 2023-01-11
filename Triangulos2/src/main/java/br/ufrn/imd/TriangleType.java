package br.ufrn.imd;

public class TriangleType
{
    public static final String EQUILATERAL = "Equilateral";

    public static final String ISOCELES = "Isoceles";

    public static final String SCALENE = "Scalene";

    public static final String NOT_TRIANGLE = "It is not a triangle";

    public String defineType(int l1, int l2, int l3)
    {
        if ((l1 > (l2 + l3)) || (l2 > (l1 + l3)) || (l3 > (l1 + l2)))
        {
            return NOT_TRIANGLE;
        }
        else if ((l1 == l2) && (l2 == l3))
        {
            return EQUILATERAL;
        }
        else if ((l1 == l2) || (l1 == l3) || (l3 == l2))
        {
            return ISOCELES;
        }
        else
        {
            return SCALENE;
        }
    }
}