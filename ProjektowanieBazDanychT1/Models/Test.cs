using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProjektowanieBazDanychT1.Models
{
    public class Test
    {
        public int Id { get; set; }
        public List<Answers> Answers { get; set; }
        public ApplicationUser UserWhoAnswered { get; set; }
        public String Title { get; set; }
        public DateTime WhenParticipated { get; set; }

    }
}
