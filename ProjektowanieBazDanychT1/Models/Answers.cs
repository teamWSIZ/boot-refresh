namespace ProjektowanieBazDanychT1.Models
{
    public class Answers
    {
        public int Id { get; set; }
        public Question Question { get; set; }
        public string Answer { get; set; }
        public bool IsCorect { get; set; }
    }
}