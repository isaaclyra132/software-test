import br.ufrn.imd.TriangleType;

/**
 * Exemplo de um caso de teste automatizado, mas implementado "à mão".
 * @author eiji
 *
 */
public class TriangleAutomaticTest
{
    public static void main(String[] args)
    {
        TriangleType triangleType = new TriangleType();

        String type = triangleType.defineType(10, 10, 10);

        boolean success = true;

        if (!type.equals(TriangleType.EQUILATERAL))
        {
            System.err.println(String.format(ERROR_MSG_FORMAT, TriangleType.EQUILATERAL, type));
            success = false;
        }

        type = triangleType.defineType(10, 15, 10);

        if (!type.equals(TriangleType.ISOCELES))
        {
            System.err.println(String.format(ERROR_MSG_FORMAT, TriangleType.ISOCELES, type));
            success = false;
        }

        type = triangleType.defineType(10, 15, 8);

        if (!type.equals(TriangleType.SCALENE))
        {
            System.err.println(String.format(ERROR_MSG_FORMAT, TriangleType.SCALENE, type));
            success = false;
        }

        type = triangleType.defineType(10, 15, 1);

        if (!type.equals(TriangleType.NOT_TRIANGLE))
        {
            System.err.println(String.format(ERROR_MSG_FORMAT, TriangleType.NOT_TRIANGLE, type));
            success = false;
        }

        if (success)
        {
            System.out.println(SUCCESS_MSG);
        }
    }

    private static final String ERROR_MSG_FORMAT = "FAILURE: Expected: %s. Returned: %s.";

    private static final String SUCCESS_MSG = "All tests passed without failure.";
}
