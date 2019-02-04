#ConcurrentQuene是一个支持并发读写的队列。
>具有阻塞读功能，并且数据在读走之后，可以自动删除，如果当前元素不存在，返回null；主要用在类似生产者-消费者模型的场合。
1. 新建对象
  ConcurrentQuene<String> concurrentQuene = QueneFactory.newConcurrentQuene();
2. 添加元素
concurrentQuene.put("000", "000");
3. 取元素
concurrentQuene.take("000");
