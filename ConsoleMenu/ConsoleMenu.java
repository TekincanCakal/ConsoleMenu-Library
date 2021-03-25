package ConsoleMenu;

import java.lang.reflect.Method;
import java.util.*;
public class ConsoleMenu
{
    class menuItem
    {
        String menuName;
        Method method;
        menuItem(String menuName, Method method)
        {
            this.menuName = menuName;
            this.method = method;
        }
    }
    private Class mainClass;
    private int delay;
    private String menuTitle;
    private char menuSymbol;
    private HashMap<Integer, menuItem> menuNames;
    public ConsoleMenu(Class mainClass)
    {
        menuNames = new HashMap<>();
        menuTitle = "Console Menu";
        menuSymbol = '-';
        delay = 1;
        this.mainClass = mainClass;
    }
    public void show()
    {
        transferTo(-1);
    }
    public void setMenuTitle(String menuTitle)
    {
        this.menuTitle = menuTitle;
    }
    public void setMenuSymbol(char menuSymbol)
    {
        this.menuSymbol = menuSymbol;
    }
    public void clear()
    {
        for(int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }
    public void setDelay(int second)
    {
        if(second >= 1 && second <= 5)
        {
            this.delay = second;
        }
    }
    public void transferTo(int index)
    {
        try
        {
            System.out.println("You will be transfered in " + delay + " second");
            Thread.sleep(1000 * delay);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if(index == -1)
        {
            clear();
            System.out.println(menuTitle);
            ArrayList<Integer> sortByIndex = new ArrayList<>(menuNames.keySet());
            Collections.sort(sortByIndex);
            for(Integer i : sortByIndex)
            {
                System.out.println(i + "" + menuSymbol + menuNames.get(i).menuName);
            }
            System.out.println();
            System.out.println("Enter a menu number:");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if(menuNames.containsKey(input))
            {
                transferTo(input);
            }
            else
            {
                System.out.println("Your enter a wrong index");
                transferTo(-1);
            }
        }
        else if(menuNames.containsKey(index))
        {
            try
            {
                menuNames.get(index).method.invoke(mainClass.newInstance());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public Method getMethod(String methodName) throws NoSuchMethodException
    {
        return mainClass.getMethod(methodName);
    }
    public void addMenu(String menuName, int index, Method method) throws Exception
    {
        if(index == -1)
        {
            throw new Exception("Index: -1 is default index, can not add");
        }
        else
        {
            menuNames.put(index, new menuItem(menuName, method));
        }
    }
    public void addMenu(String menuName, int index, String methodName) throws Exception
    {
        addMenu(menuName, index, getMethod(methodName));
    }
}
