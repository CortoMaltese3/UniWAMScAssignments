#include <stdio.h>
main()
{
    int i, num, status, child;
    if (fork() != 0) //γονέας
    {
      child = wait(&status);
      printf("I'm the parent.PID=%d, PPID=%d.\n", getpid(), getppid());
      printf("My child is PID %d. It just exited with number %d\n", child, status >> 8);
      exit(18);
    }
    else // παιδί
    {
      num = 0;
      for (i = 1; i <= 10000; i++)
        num = num + i;
      printf("Num is : % d\n",num);
      sleep(4);
      printf("I'm the child. PID=%d, PPID=%d.\n", getpid(), getppid());
      exit(223);
    }
}
